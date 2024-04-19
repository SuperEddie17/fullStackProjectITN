import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import { apiGet, apiPost, apiPut } from "../utils/api";

import InputField from "../components/InputField";
import FlashMessage from "../components/FlashMessage";
import InputSelect from "../components/InputSelect";


const InvoiceForm = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const [invoice, setInvoice] = useState({
        invoiceNumber: "",
        seller: { _id: "" },
        buyer: { _id: "" },
        issued: "",
        dueDate: "",
        product: "",
        price: "",
        vat: "",
        note: "",
    });

    const [errorState, setError] = useState(null);
    /*const [sellerState, setSeller] = useState([]);
    const [buyerState, setBuyer] = useState([]); */

    const [sentState, setSent] = useState(false);
    const [successState, setSuccess] = useState(false);
    const [personList, setPersonList] = useState([]);

    useEffect(() => {
        if (id) {
            apiGet("/api/invoices/" + id).then((data) => {
                setInvoice(data);
               /* setSeller(data.seller._id);
                setBuyer(data.buyer_id); */
            }
            );
        };
        apiGet("/api/persons").then((data) => setPersonList(data));
    }, [id]);

    const handleSubmit = (e) => {
        e.preventDefault();



        (id ? apiPut("/api/invoices/" + id, invoice) : apiPost("/api/invoices", invoice))
            .then((data) => {
                setSent(true);
                setSuccess(true);
                navigate("/invoices");
            })
            .catch((error) => {
                console.log(error.message);
                setError(error.message);
                setSent(true);
                setSuccess(false);
            });
    };

    const sent = sentState;
    const success = successState;

    return (
        <div>
            <h1>{id ? "Upravit" : "Vytvořit"} Faktura</h1>
            <hr />
            {errorState ? (
                <div className="alert alert-danger">{errorState}</div>
            ) : null}
            {sent && (
                <FlashMessage
                    theme={success ? "success" : ""}
                    text={success ? "Uložení faktury proběhlo úspěšně." : ""}
                />
            )}
            <form onSubmit={handleSubmit}>
                <InputField
                    required={true}
                    type="text"
                    name="invoiceNumber"
                    min="3"
                    label="Cislo faktury"
                    prompt="Zadejte cislo faktury"
                    value={invoice.invoiceNumber}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, invoiceNumber: e.target.value });
                    }}
                />


                <InputSelect
                    required={true}
                    multiple={false}
                    name="seller"
                    label="prodavajici"
                    prompt="Vyberte prodejce"
                    items={personList}
                    value={invoice.seller._id}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, seller: { _id: e.target.value } });
                    }}

                />



                <InputSelect
                    required={true}
                    multiple={false}
                    name="buyer"
                    label="Kupujici"
                    prompt="Vyberte kupujiciho"
                    items={personList}
                    value={invoice.buyer._id}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, buyer: { _id: e.target.value } });
                    }}

                />
                <InputField
                    required={true}
                    type="date"
                    name="issued"
                    min="1"
                    label="Datum vystaveni faktury"
                    prompt="Zadejte datum vystaveni faktury"
                    value={invoice.issued}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, issued: e.target.value });
                    }}
                />

                <InputField
                    required={true}
                    type="date"
                    name="dueDate"
                    min="1"
                    label="Datum splatnosti"
                    prompt="Zadejte datum splatnosti faktury"
                    value={invoice.dueDate}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, dueDate: e.target.value });
                    }}
                />

                <InputField
                    required={true}
                    type="text"
                    name="product"
                    min="1"
                    label="Produkt"
                    prompt="Zadejte produkt nebo sluzbu"
                    value={invoice.product}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, product: e.target.value });
                    }}
                />

                <InputField
                    required={true}
                    type="number"
                    name="price"
                    min="1"
                    label="Cena"
                    prompt="Zadejte cenu"
                    value={invoice.price}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, price: e.target.value });
                    }}
                />

                <InputField
                    required={true}
                    type="text"
                    name="vat"
                    min="1"
                    label="VAT"
                    prompt="Zadejte DPH"
                    value={invoice.vat}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, vat: e.target.value });
                    }}
                />

                <InputField
                    required={false}
                    type="text"
                    name="note"
                    min=""
                    label="Poznamka"
                    prompt="Zadejte poznamku"
                    value={invoice.note}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, note: e.target.value });
                    }}
                />


                <input type="submit" className="btn btn-primary" value="Uložit" />
            </form>
        </div>
    );
};

export default InvoiceForm;