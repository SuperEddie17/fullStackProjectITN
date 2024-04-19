

const StatisticsIndex = () => {
    const [statistics, setStatistics] = useState([]);
    

    useEffect(() => {
        
           
        async function fetchStatistics() {
            const StatisticData = await apiGet("/api/persons/statistics");
            setStatistics(StatisticData);           
                       
        };

        
        fetchStatistics();
    }, [id]);

}