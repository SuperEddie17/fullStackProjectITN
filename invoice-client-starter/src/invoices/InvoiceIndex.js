import React, {useEffect, useState} from "react";
import InvoiceTable from "./InvoiceTable";
import { apiDelete, apiGet } from "../utils/api";

const InvoiceIndex = () => {
    const[invoices, setInvoices] = useState([]);

    const deleteInvoice = async (id) => {
        try {
            await apiDelete("/api/invoices/" + id); 
            setInvoices(prevInvoices => prevInvoices.filter(item => item._id !== id)); // Aktualizace stavu faktur po smazání
        } catch (error) {
            console.log(error.message);
            alert(error.message);
        }
    };

    useEffect(() => {
        apiGet("/api/invoices").then((data) => setInvoices(data));
    }, []);

    return (
        <div>
            <h1>Seznam faktur</h1>
            <InvoiceTable
                deleteInvoice={deleteInvoice}
                items={invoices}
                label={"Pocet faktur"}
                />
        </div>
    );
};
export default InvoiceIndex;