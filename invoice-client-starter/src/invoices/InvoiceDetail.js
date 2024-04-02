import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

import {apiGet} from "../utils/api";

const InvoiceDetail = () => {
    const {id} = useParams();
    const [invoice, setInvoice] = useState({});

    useEffect(() => {
        apiGet("/api/invoices/"+id).then((data) => setInvoice(data));
    }, [id]);

    return (
    <>
        <div>
            <h1>Detail faktury</h1>
            <hr/>
            <h3>{invoice.invoiceNumber}</h3>
            <p>
                {invoice.price}

            </p>
        </div>
    </>    
    ); 
};
export default InvoiceDetail;