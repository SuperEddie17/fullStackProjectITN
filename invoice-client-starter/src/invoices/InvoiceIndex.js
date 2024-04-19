import React, { useEffect, useState } from "react";
import InvoiceTable from "./InvoiceTable";
import { apiDelete, apiGet } from "../utils/api";
import InvoiceFilter from "./InvoiceFilter";

const InvoiceIndex = () => {
    const [invoices, setInvoices] = useState([]);
    const [personListState, setPersonList] = useState([]);
    const [statistics, setStatistics] = useState(null);
    const [filterState, setFilter] = useState({
        sellerID: undefined,
        buyer: undefined,
        product: undefined,
        minPrice: undefined,
        maxPrice: undefined,
        limit: undefined
    });

    useEffect(() => {
        apiGet("/api/invoices").then((data) => setInvoices(data));
        apiGet("/api/invoices/statistics").then((data) => setStatistics(data));
        apiGet("/api/persons").then((data) => setPersonList(data));

    }, []);

    const handleChange = (e) => {

        if (e.target.value === "false" || e.target.value === "true" || e.target.value === '') {
            setFilter(prevState => {
                return { ...prevState, [e.target.name]: undefined }
            });
        } else {
            setFilter(prevState => {
                return { ...prevState, [e.target.name]: e.target.value }
            });
        }
    };
    const handleSubmit = async (e) => {
        e.preventDefault();
        const params = filterState;

        const data = await apiGet("/api/invoices", params);
        setInvoices(data);
    };

    const deleteInvoice = async (id) => {
        try {
            await apiDelete("/api/invoices/" + id);
            setInvoices(prevInvoices => prevInvoices.filter(item => item._id !== id)); // Aktualizace stavu faktur po smazání
        } catch (error) {
            console.log(error.message);
            alert(error.message);
        }
    };

    return (
        <div>
            <h1>Seznam faktur</h1>
            {statistics && (
                <>
                    <p>Suma za aktualni rok: {statistics.currentYearSum} Kc </p>
                    <p>Suma celkove: {statistics.allTimeSum} Kc</p>
                </>
            )}
            <InvoiceFilter
                handleChange={handleChange}
                handleSubmit={handleSubmit}
                personList={personListState}
                filter={filterState}
                confirm="Filtrovat faktury"
            />

            <InvoiceTable
                deleteInvoice={deleteInvoice}
                items={invoices}
                label={"Pocet faktur"}
            />

        </div>
    );
};
export default InvoiceIndex;