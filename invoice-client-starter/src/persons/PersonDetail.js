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

import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { currencyFormatter } from "../utils/currencyFormatter";
import { apiGet } from "../utils/api";
import Country from "./Country";
import SalesAndPurchasesTable from "../invoices/SalesAndPurchasesTable";

const PersonDetail = () => {
    const { id } = useParams();
    const { identificationNumber } = useParams();
    const [person, setPerson] = useState({});
    const [purchases, setPurchases] = useState({});
    const [sales, setSales] = useState({});
    const [statistics, setStatistics] = useState([]);

    //get pozadavky pro persons a statistics 
    useEffect(() => {
        async function fetchPersons() {
            const personData = await apiGet("/api/persons/" + id);
            setPerson(personData);
        };
        async function fetchStatistics() {
            const StatisticData = await apiGet("/api/persons/statistics");
            setStatistics(StatisticData);
            const personStatistics = StatisticData.find((item) => item.personId === parseInt(id));
            setStatistics(personStatistics);
        };
        fetchPersons();
        fetchStatistics();
    }, [id]);

    //get pozadavky na ziskani sales a purchases
    useEffect(() => {
        async function fetchSales() {

            const salesData = await apiGet("/api/identification/" + person.identificationNumber + "/sales");
            setSales(salesData);
            console.log("SalesData:", salesData);


        };

        async function fetchPurchases() {

            const purchasesData = await apiGet("/api/identification/" + person.identificationNumber + "/purchases");
            setPurchases(purchasesData);
            console.log("PurchasesData:", purchasesData);

        };
        fetchSales();
        fetchPurchases();
    }, [person]);

    //pokud se nenactou data, vypise se nacitaci zprava
    if (!sales) {
        return <p>Načítám</p>
    };
    const country = Country.CZECHIA === person.country ? "Česká republika" : "Slovensko";

    return (
        <>
            <h1>Detail osoby</h1>
            <hr />
            <div className="container">
                <div className="row">
                    <div className="col-md-6">

                        <h3>{person.name} ({person.identificationNumber})</h3>
                        <p>
                            <strong>DIČ:</strong>
                            <br />
                            {person.taxNumber}
                        </p>

                        <p>
                            <strong>Bankovní účet:</strong>
                            <br />
                            {person.accountNumber}/{person.bankCode} ({person.iban})
                        </p>
                        <p>
                            <strong>Tel.:</strong>
                            <br />
                            {person.telephone}
                        </p>
                        <p>
                            <strong>Mail:</strong>
                            <br />
                            {person.mail}
                        </p>
                        <p>
                            <strong>Sídlo:</strong>
                            <br />
                            {person.street}, {person.city},
                            {person.zip}, {country}
                        </p>
                        <p>
                            <strong>Poznámka:</strong>
                            <br />
                            {person.note}
                        </p>

                        <p>
                            <strong>Tržba:</strong>
                            <br />
                            {currencyFormatter.format(statistics.revenue)}
                        </p>
                    </div>


                    <div className="col-md-6 d-flex align-items-left justify-content-center flex-column">
                        <SalesAndPurchasesTable data={sales} title="Vydané" />


                        <SalesAndPurchasesTable data={purchases} title="Přijaté" />
                    </div>
                </div>
            </div>




        </>
    );
};

export default PersonDetail;
