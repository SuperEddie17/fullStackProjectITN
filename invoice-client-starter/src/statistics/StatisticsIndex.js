import React, { useEffect, useState } from "react";
import { apiGet } from "../utils/api";
import StatisticsTable from "./StatisticsTable";
const StatisticsIndex = () => {
    const [statistics, setStatistics] = useState([]);
    

    useEffect(() => {
        
        apiGet("/api/persons/statistics").then((data) => setStatistics(data));
    }, []);

    console.log ("statistiky",statistics);

    return (
        <><h1>Statistiky spolecnosti</h1>
        <div>
            <StatisticsTable data={statistics}/>
        </div>
        </>
    )

}
export default StatisticsIndex;