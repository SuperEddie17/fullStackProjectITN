package cz.itnetwork.entity.repository.specification;

import cz.itnetwork.dto.InvoiceFilter;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.InvoiceEntity_;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.PersonEntity_;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class InvoiceSpecification implements Specification<InvoiceEntity> {
    private final InvoiceFilter filter;

    /**
     * Filtr faktur
     * @param root kořenový objekt pro query
     * @param query kritéria dotazu
     * @param criteriaBuilder bulider kritérií
     * @return filtrace faktur
     */
    @Override
    public Predicate toPredicate(Root<InvoiceEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        //filtrování podle minimální ceny
        if(filter.getMinPrice() != null){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(InvoiceEntity_.PRICE), filter.getMinPrice()));
        }

        //filtrování podle maximální ceny
        if(filter.getMaxPrice() != null){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(InvoiceEntity_.PRICE), filter.getMaxPrice()));
        }

        //filtrování podle odběratele
        if(filter.getBuyerID()!= null){
            Join<PersonEntity, InvoiceEntity> buyerJoin = root.join(InvoiceEntity_.BUYER);
            predicates.add(criteriaBuilder.equal(buyerJoin.get(PersonEntity_.ID), filter.getBuyerID()));

        }

        //filtrování podle dodavatele
        if(filter.getSellerID() != null){
            Join<PersonEntity, InvoiceEntity> sellerJoin = root.join(InvoiceEntity_.SELLER);
            predicates.add(criteriaBuilder.equal(sellerJoin.get(PersonEntity_.ID), filter.getSellerID()));
        }

        //filtrování podle názvu produktu
        if(filter.getProduct() != null){
            predicates.add(criteriaBuilder.like(root.get(InvoiceEntity_.PRODUCT), "%"+filter.getProduct()+"%"));
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
