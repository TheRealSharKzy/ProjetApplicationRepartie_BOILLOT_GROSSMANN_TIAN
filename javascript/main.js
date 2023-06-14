window.addEventListener('load', function () {

    // Créer une instance de carte
    var map = L.map('map');

    // Définir la vue initiale et le niveau de zoom
    map.setView([48.6921, 6.1844], 13);

    // Ajouter une couche de tuile (par exemple, OpenStreetMap)
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors',
    }).addTo(map);

    const url='https://transport.data.gouv.fr/gbfs/nancy/station_information.json'
    fetch(url).then((response) => response.json()).then((data) => {
        const stations = data['data']['stations'];
        stations.forEach(station => {
            const marker = L.marker([station['lat'], station['lon']]).addTo(map);
            marker.bindPopup(station['name']);
        });
    });

});