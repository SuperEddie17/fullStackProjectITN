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
package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    //přidaní osoby
    @PostMapping("")
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO) {
        return personService.addPerson(personDTO);
    }

    //vyppsání všech osob
    @GetMapping("")
    public List<PersonDTO> getPersons() {
        return personService.getAll();
    }

    //smazání osoby, po smazání vypsaní 204 no content statusu
    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable long personId) {
        personService.removePerson(personId);
    }

    //zobrazení urcřité osoby dle jejího IČ
    @GetMapping("/{personId}")
    public PersonDTO getPerson(@PathVariable long personId) {
        return personService.getPersonById(personId);
    }

    //upravení osoby dle jejího IČ
    @PutMapping("/{personId}")
    public PersonDTO editPerson(@PathVariable long personId,@RequestBody PersonDTO personDTO) {
        return personService.editPerson(personId,personDTO);
    }

    //zobrazení přijatých faktur pro určitou osobu dle jejího IČ
    @GetMapping("/identification/{identificationNumber}/purchases")
    public List<InvoiceDTO> getPurchasesByIdentificationNumber(@PathVariable String identificationNumber){
        return personService.invoiceByBuyer(identificationNumber);
    }

    //zobrazení vystavených faktur pro určitou osobu dle jejího IČ
    @GetMapping("/identification/{identificationNumber}/sales")
    public List<InvoiceDTO> getSalesByIdentificationNumber(@PathVariable String identificationNumber){
        return personService.invoiceBySeller(identificationNumber);
    }


}

