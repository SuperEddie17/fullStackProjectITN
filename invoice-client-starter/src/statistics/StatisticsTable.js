import React from "react";
import { currencyFormatter } from "../utils/currencyFormatter";

const StatisticsTable = ({ data }) => {

    return (
        <>
            <div className="table-responsive">
                <table className="table table-bordered">
                    <thead>
                        <tr>
                            <th>Osoba</th>
                            <th>Tržba</th>
                        </tr>
                    </thead>
                    <tbody>
                        {data.map(item => (
                            <tr key={item.personId} >
                                <td>{item.personName}</td>
                                <td>{currencyFormatter.format(item.revenue)}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </>

    );

};

export default StatisticsTable;