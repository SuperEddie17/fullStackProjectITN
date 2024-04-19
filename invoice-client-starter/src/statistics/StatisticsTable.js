import React from "react";


const StatisticsTable = ({data}) => {


    return (
        <>               
        <div className="table-responsive">
        <table className="table table-bordered">
            <thead>
                <tr>
                    <th>Spolecnost</th>
                    <th>Trzba</th>                    
                </tr>
            </thead>
            <tbody>
            {data.map(item => (
                        <tr key={item.personId} >
                            <td>{item.personName}</td>
                            <td>{item.revenue}</td>
                        </tr>
                 ))}
            </tbody>           
            </table>
        </div>

        </>

    );

};

export default StatisticsTable;