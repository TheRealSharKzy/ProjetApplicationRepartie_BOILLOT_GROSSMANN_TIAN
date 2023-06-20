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

    const stationInformation = await fetch(urlStationInformation).then(response=>{
        if(response.ok)
            return response.json();
        else
            return Promise.reject(new Error(response.statusText))
    }).catch(error => console.log('fetch: net error :' + error.message));
    const stationStatus=await fetch(urlStationStatus).then(response=>{
        if(response.ok)
            return response.json();
        else
            return Promise.reject(new Error(response.statusText))
    }).catch(error => console.log('fetch: net error :' + error.message));
    const tableStationInformation=stationInformation['data']['stations'];
    const tableStationStatus=stationStatus['data']['stations'];
    for (i=0;i<tableStationInformation.length;i++){
        //console.log(tableStationImformation[i]['lon']);
        const marker=L.marker([tableStationInformation[i]['lat'],tableStationInformation[i]['lon']],{
            icon: L.icon({
                iconUrl: 'https://www.android-logiciels.fr/wp-content/uploads/2014/07/Paris-Velib-icone-1.png',
                iconSize: [25, 25],
                iconAnchor: [12.5, 25],
                popupAnchor: [0, -12.5],
            }),
        }).addTo(map);
        let markerPopup=tableStationInformation[i]['name'];
        markerPopup+='<br>nombre de vélos disponible: '+tableStationStatus[i]['num_bikes_available'];
        markerPopup+='<br>nombre de place de parkings libre: '+tableStationStatus[i]['num_docks_available'];
        marker.bindPopup(markerPopup);
    }

    const urlConstruction='https://carto.g-ny.org/data/cifs/cifs_waze_v2.json';
    fetch(urlConstruction).then((response)=>response.json()).then((data)=>{
        const incidents=data["incidents"];
        incidents.forEach(incident=>{
            const marker=L.marker(incident['location']['polyline'].split(' '),{
                icon: L.icon({
                    iconUrl: 'https://openclipart.org/image/800px/284756',
                    iconSize: [25, 25],
                }),
            }).addTo(map);
            let markerPopup="date: "+incident['starttime']+" - "+incident['endtime'];
            markerPopup+="<br>cause: "+incident['type'];
            marker.bindPopup(markerPopup);
        });
    });

   const urlDonneesBloquees='http://localhost:8080/donneesBloquees';
    fetch(urlDonneesBloquees).then((response)=>response.json()).then((data)=>{
        data.forEach(donnee=>{
            const coordinates=donnee['geometry']['coordinates'];
            const marker=L.marker([coordinates[1],coordinates[0]],{
                icon: L.icon({
                    iconUrl: 'https://cdn-icons-png.flaticon.com/512/2883/2883921.png',
                    iconSize: [25, 25],
                }),
            }).addTo(map);
            let markerPopup=donnee['fields']['uo_lib_officiel'];
            marker.bindPopup(markerPopup);
        });
    });

    const URLrestaurants='http://localhost:8080/restaurants';
    fetch(URLrestaurants).then((response)=>response.json()).then((data)=>{
        data['restaurants'].forEach(donnee=>{
            const longitude = donnee['longitude'];
            const latitude = donnee['latitude'];
            const marker=L.marker([latitude,longitude],{
                icon: L.icon({
                    iconUrl: 'https://cdn.icon-icons.com/icons2/2419/PNG/512/restaurant_location_icon_146860.png',
                    iconSize: [25, 25],
                    iconAnchor: [12.5, 25],
                    popupAnchor: [0, -12.5],
                }),
            }).addTo(map);
        });
    });

});