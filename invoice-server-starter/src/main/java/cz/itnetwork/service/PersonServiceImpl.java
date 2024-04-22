/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;


    /**
     * přidání nové osoby
     * @param personDTO vytvářená osoba
     * @return nová osoba
     */
    public PersonDTO addPerson(PersonDTO personDTO) {
        PersonEntity entity = personMapper.toEntity(personDTO);
        entity = personRepository.save(entity);

        return personMapper.toDTO(entity);
    }

    /**
     * smazání osoby
     * @param personId id osoby ke smazání
     */
    @Override
    public void removePerson(long personId) {
        try {
            PersonEntity person = fetchPersonById(personId);
            person.setHidden(true);
            //osoba je nastavena jako hidden, není smazána, zůstává v databázi

            personRepository.save(person);
        } catch (NotFoundException ignored) {

        }
    }

    /**
     * vypsání všech osob
     * @return vrátí list všech neskrytých osob
     */
    @Override
    public List<PersonDTO> getAll() {
        return personRepository.findByHidden(false)
                .stream()
                .map(i -> personMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    /**
     * nalezení osoby dle jejího ID
     * @param personId id hledané osoby
     * @return vrátí entitu hledané osoby
     */
    @Override
    public PersonDTO getPersonById(long personId) {
        PersonEntity personEntity = fetchPersonById(personId);

        return personMapper.toDTO(personEntity);
    }

    /**
     * editace osoby
     * @param id id editované osoby
     * @param editedPerson editovaný objekt osoby
     * @return vytvoří novou osobu s novým ID, původní osobu skryje
     */
    @Override
    public PersonDTO editPerson(long id, PersonDTO editedPerson) {

        PersonEntity person = fetchPersonById(id);
        person.setHidden(true);
        //skrytí původní osoby
        personRepository.save(person);

        return addPerson(editedPerson) ;
    }

    /**
     * vypsání vydaných faktur pro danou osobu
     * @param identificationNumber IČO dodavatele
     * @return vrátí seznam vydaných faktur danou osobou
     */
    @Override
    public List<InvoiceDTO> invoiceBySeller(String identificationNumber) {
            return personRepository.findByIdentificationNumber(identificationNumber)
                    .stream()
                    .map(PersonEntity::getSales)
                    .flatMap(java.util.Collection::stream)
                    .map(invoiceMapper::toDTO)
                    .collect(Collectors.toList());
    }

    /**
     * vypsání přijatých faktur danou osobou
     * @param identificationNumber IČO odběratele
     * @return vrátí seznam přijatých faktur danou osobou
     */
    @Override
    public List<InvoiceDTO> invoiceByBuyer(String identificationNumber) {
        return personRepository.findByIdentificationNumber(identificationNumber)
                .stream()
                .map(PersonEntity::getPurchases)
                .flatMap(java.util.Collection::stream)
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * <p>Attempts to fetch a person.</p>
     * <p>In case a person with the passed [id] doesn't exist a [{@link org.webjars.NotFoundException}] is thrown.</p>
     *
     * @param id Person to fetch
     * @return Fetched entity
     * @throws org.webjars.NotFoundException In case a person with the passed [id] isn't found
     */

    /**
     * nalezení osoby dle ID
     * @param id ID hledané osoby
     * @return entitu hledané osoby
     */
    @Override
    public PersonEntity fetchPersonById(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }


}
