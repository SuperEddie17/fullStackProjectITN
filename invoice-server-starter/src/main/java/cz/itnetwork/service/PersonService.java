package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.entity.PersonEntity;

import java.util.List;

public interface PersonService {

    /**
     * Vytvoří novou fakturu
     *
     * @param personDTO vytvářená osoba
     * @return vrátí novou osobu
     */
    PersonDTO addPerson(PersonDTO personDTO);

    /**
     * <p>nastaví osobu jako "hidden" s odpovídajícím [id]</p>
     * <p>V případě, že osoba s [id] není nalezena, poté metoda <b>tiše selže</b></p>
     *
     * @param id id osoby ke smazání
     */
    void removePerson(long id);

    /**
     * Získá všechny neskryté osoby
     *
     * @return List všech neskrytých osob
     */
    List<PersonDTO> getAll();

    /**
     * nalezení osoby dle ID
     * @param id id hledané osoby
     * @return objekt nalezené osoby
     */
    PersonDTO getPersonById(long id);

    /**
     * editace osoby dle ID
     * @param id id editované osoby
     * @param personDTO editovaný objekt osoby
     * @return upravená osoba
     */
    PersonDTO editPerson(long id,PersonDTO personDTO);

    /**
     * vypsání vystavených faktur pro určitou osobu dle IČO
     * @param identificationNumber IČO dodavatele
     * @return seznam vystavených faktur
     */
    List<InvoiceDTO> invoiceBySeller(String identificationNumber );

    /**
     * vypsání přijatých faktur pro určitou osobu dle IČO
     * @param identificationNumber IČO odběratele
     * @return seznam přijatých faktur
     */
    List<InvoiceDTO> invoiceByBuyer(String identificationNumber);

    /**
     * nalezení osoby dle ID
     * @param id ID hledané osoby
     * @return entita hledané osoby
     */
    PersonEntity fetchPersonById(long id);




}
