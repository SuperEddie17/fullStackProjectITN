import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import dateStringFormatter from "../utils/dateStringFormatter";

import { apiGet } from "../utils/api";

const InvoiceDetail = () => {
    const { id } = useParams();
    const [invoice, setInvoice] = useState({});

    useEffect(() => {
        apiGet("/api/invoices/" + id).then((data) => setInvoice(data));
    }, [id]);

    return (
        <>
            <div>
                <h1>Detail faktury</h1>
                <hr />
                <h3> Cislo faktury: {invoice.invoiceNumber} <br /> </h3>

                <p>
                    <strong>Cena:</strong> <br />
                    {invoice.price} Kc <br />
                    <strong> DPH:</strong> <br />
                    {invoice.vat}% <br />
                    <strong>Prodavajici:</strong> <br /> {invoice.seller ? invoice.seller.name : 'Neznámý'} <br />
                    <strong> Nakupujici:</strong> <br /> {invoice.buyer ? invoice.buyer.name : 'Neznámý'} <br />
                    <strong> Datum vystaveni:</strong> <br /> {dateStringFormatter(invoice.issued)} <br />
                    <strong> Datum splatnosti:</strong> <br /> {dateStringFormatter(invoice.dueDate)} <br />
                    <strong> Produkt:</strong> <br /> {invoice.product}<br />
                    <strong> Poznamka:</strong> <br /> {invoice.note}<br />
                </p>
            </div>





        </>
    );
};
export default InvoiceDetail;