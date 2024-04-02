
import React from "react";
import {Link} from "react-router-dom";
import dateStringFormatter from "../utils/dateStringFormatter";
import price from "../utils/price";

const InvoiceTable = ({label, items, deleteInvoice}) => {
    return (
       <div>
        <p>
            {label} {items.length}
        </p>       

       <table className = "table table-bordered">
         <thead>
            <tr>
                <th>#</th>
                <th>Cislo faktury</th>
                <th>Prodavajici</th>
                <th>Nakupujici</th>
                <th>Cena</th>
                <th>Datum porizeni</th>
                <th>Datum splatnosti</th>
                <th>Poznamka</th>
                <th colSpan={3}>Akce</th>          
            </tr>
            </thead>
            <tbody>
                {items.map((item, index) => (
                    <tr key = {index +1} >
                        <td>{index + 1}</td>
                        <td>{item.invoiceNumber}</td>
                        <td>{item.seller?.name}</td>
                        <td>{item.buyer?.name}</td>
                        <td>{item.price}</td>
                        <td>{dateStringFormatter(item.issued)}</td>
                        <td>{dateStringFormatter(item.dueDate)}</td>
                        <td>{item.note}</td>

                        <td>
                            <div className = "btn-group">
                                <Link
                                    to={"/invoices/show/"+item._id}
                                    className = "btn btn-sm btn-info"
                                >
                                    Zobrazit
                                </Link>
                                <Link
                                    to={"/invoices/edit/"+item._id}
                                    className="btn btn-sm btn-warning"
                                >
                                  Upravit
                                    </Link>
                                <button
                                    onClick = {() => deleteInvoice(item._id)}
                                    className = "btn btn-sm btn-danger"
                                >
                                    Odstranit
                                </button>
                            </div>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <Link to={"/invoices/create"} className = "btn btn-success">
                Nova faktura
            </Link>
            </div>
    );
};

export default InvoiceTable;