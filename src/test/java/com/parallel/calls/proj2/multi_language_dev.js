// JavaScript - Fetch weather API
fetch('https://api.weather.com/v1?location=NY')
    .then(response => response.json())
    .then(data => console.log(data));