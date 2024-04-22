import { currencyFormatter } from "../utils/currencyFormatter";
import dateStringFormatter from "../utils/dateStringFormatter";
import { Link } from "react-router-dom";
const SalesAndPurchasesTable = ({ data, title }) => {
  const columnTitle = title === "Vydané" ? "Odběratel" : "Dodavatel";
  const buyerOrSeller = columnTitle === "Odběratel" ? "buyer" : "seller";

  return (
    <div>
      <h3>{title} faktury</h3>
      {Array.isArray(data) && data.length > 0 ? (
        <div className="table-responsive">
          <table className="table table-bordered">
            <thead>
              <tr>
                <th>Číslo faktury</th>
                <th>{columnTitle} </th>
                <th>Cena</th>
                <th>Datum vystavení</th>
                <th>Datum splatnosti</th>
              </tr>
            </thead>
            <tbody>
              {data.map(item => (
                <tr key={item.invoiceNumber}>
                  <td><Link to={"/invoices/show/" + item._id} > {item.invoiceNumber} </Link> </td>
                  <td>{item[buyerOrSeller].name}</td>
                  <td>{currencyFormatter.format(item.price)}</td>
                  <td>{dateStringFormatter(item.issued)}</td>
                  <td>{dateStringFormatter(item.dueDate)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ) : (
        <>
          <strong>Žádná data nejsou k dispozici</strong>
          <br />
          <br />
        </>
      )}


    </div>
  );
};

export default SalesAndPurchasesTable;