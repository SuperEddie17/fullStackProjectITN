import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import dateStringFormatter from "../utils/dateStringFormatter";
import { currencyFormatter } from "../utils/currencyFormatter";
import { apiGet } from "../utils/api";

const InvoiceDetail = () => {
    const { id } = useParams();
    const [invoice, setInvoice] = useState({});

    //get pozadavek na databazi
    useEffect(() => {
        apiGet("/api/invoices/" + id).then((data) => setInvoice(data));
    }, [id]);

    return (
        <>
            <div>
                <h1>Detail faktury</h1>
                <hr />
                <h3> Číslo faktury: {invoice.invoiceNumber} <br /> </h3>

                <p>
                    <strong>Cena:</strong> <br />
                    {currencyFormatter.format(invoice.price)}<br />
                    <strong> DPH:</strong> <br />
                    {invoice.vat}% <br />
                    <strong> Dodavatel:</strong> <br /> {invoice.seller ? invoice.seller.name : 'Neznámý'} <br />
                    <strong> Odběratel:</strong> <br /> {invoice.buyer ? invoice.buyer.name : 'Neznámý'} <br />
                    <strong> Datum vystavení:</strong> <br /> {dateStringFormatter(invoice.issued)} <br />
                    <strong> Datum splatnosti:</strong> <br /> {dateStringFormatter(invoice.dueDate)} <br />
                    <strong> Produkt:</strong> <br /> {invoice.product}<br />
                    <strong> Poznámka:</strong> <br /> {invoice.note}<br />
                </p>
            </div>





        </>
    );
};
export default InvoiceDetail;