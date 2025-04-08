import requests

def fetch_weather():
    response = requests.get('https://api.weather.com/v1?location=NY')
    if response.status_code == 200:
        print(response.json())