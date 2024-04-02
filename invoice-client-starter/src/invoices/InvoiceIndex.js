import React, {useEffect, useState} from "react";
import InvoiceTable from "./InvoiceTable";
import { apiGet } from "../utils/api";

const InvoiceIndex = () => {
    const[invoices, setInvoices] = useState([]);

    useEffect(() => {
        apiGet("/api/invoices").then((data) => setInvoices(data));
    }, []);

    return (
        <div>
            <h1>Seznam faktur</h1>
            <InvoiceTable
                items={invoices}
                label={"Pocet faktur"}
                />
        </div>
    );
};
export default InvoiceIndex;