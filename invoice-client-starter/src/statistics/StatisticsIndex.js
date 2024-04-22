import React, { useEffect, useState } from "react";
import { apiGet } from "../utils/api";
import StatisticsTable from "./StatisticsTable";
const StatisticsIndex = () => {
    const [statistics, setStatistics] = useState([]);

    //get pozadavek na statistics
    useEffect(() => {

        apiGet("/api/persons/statistics").then((data) => setStatistics(data));
    }, []);

    return (
        <><h1>Statistiky osob</h1>
            <div>
                <StatisticsTable data={statistics} />
            </div>
        </>
    )

}
export default StatisticsIndex;