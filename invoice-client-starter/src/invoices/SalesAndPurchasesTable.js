import dateStringFormatter from "../utils/dateStringFormatter";
const SalesAndPurchasesTable = ({ data, title }) => {
  const columnTitle = title === "Vydane" ? "Odberatel" : "Dodavatel";
  return (
    <div>
      <h3>{title} faktury</h3>
      {Array.isArray(data) && data.length > 0 ? (
        <div className="table-responsive">
          <table className="table table-bordered">
            <thead>
              <tr>
                <th>Cislo faktury</th>
                <th>{columnTitle} </th>
                <th>Cena</th>
                <th>Datum vystaveni</th>
                <th>Datum splatnosti</th>
              </tr>
            </thead>
            <tbody>
              {data.map(item => (
                <tr key={item.invoiceNumber}>
                  <td>{item.invoiceNumber}</td>
                  <td>{item.buyer.name}</td>
                  <td>{item.price}</td>
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