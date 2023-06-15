window.addEventListener('load', async function () {

    // Créer une instance de carte
    var map = L.map('map');

    // Définir la vue initiale et le niveau de zoom
    const urlGeometry = 'https://api-adresse.data.gouv.fr/search/?q=nancy&postcode=54000';
    fetch(urlGeometry).then((response) => response.json()).then((data) => {
        const coordinates = data['features'][0]['geometry']['coordinates'];
        map.setView([coordinates[1], coordinates[0]], 13);
    });

    // Ajouter une couche de tuile (par exemple, OpenStreetMap)
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors',
    }).addTo(map);

    const urlStationInformation = 'https://transport.data.gouv.fr/gbfs/nancy/station_information.json';
    const urlStationStatus = 'https://transport.data.gouv.fr/gbfs/nancy/station_status.json';
    /*fetch(urlStationInformation).then((response) => response.json()).then((data) => {
        const stations = data['data']['stations'];
        stations.forEach(station => {
            const marker = L.marker([station['lat'], station['lon']]).addTo(map);
            const markerPopup=station['name'];
            marker.bindPopup(markerPopup);
            fetch(urlStationStatus).then((response)=>response.json()).then((data)=>{
               const stations=data['data']['stations'];

            });
        });
    });*/

    const stationInformation = await fetch(urlStationInformation).then(response=>{
        if(response.ok)
            return response.json();
        else
            return Promise.reject(new Error(response.statusText))
    }).catch(error => console.log('fetch: net error :' + error.message));
    const stationStatus=await fetch(urlStationStatus).then(response=>response.json());
    const tableStationInformation=stationInformation['data']['stations'];
    const tableStationStatus=stationStatus['data']['stations'];
    for (i=0;i<tableStationInformation.length;i++){
        //console.log(tableStationInformation[i]['lon']);
        const marker=L.marker([tableStationInformation[i]['lat'],tableStationInformation[i]['lon']]).addTo(map);
        let markerPopup=tableStationInformation[i]['name'];
        markerPopup+='<br>nombre de vélos disponible: '+tableStationStatus[i]['num_bikes_available'];
        markerPopup+='<br>nombre de place de parkings libre: '+tableStationStatus[i]['num_docks_available'];
        marker.bindPopup(markerPopup);
    }

    const urlConstruction='https://carto.g-ny.org/data/cifs/cifs_waze_v2.json';
    fetch(urlConstruction).then((response)=>response.json()).then((data)=>{
        const incidents=data["incidents"];
        incidents.forEach(incident=>{
            const marker=L.marker(incident['location']['polyline'].split(' ')).addTo(map);
            let markerPopup="date: "+incident['starttime']+" - "+incident['endtime'];
            markerPopup+="<br>cause: "+incident['type'];
            marker.bindPopup(markerPopup);
        });
    });

});