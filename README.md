# SampleAndroidTask
# Main App Components:
# Jetpack compose:
  Jetpack compose is used for UI making. No xml files are used.

# RoomDatabase:
  Roomdatabase is used to store user location and weather display type i.e celcius or fehrenheit

# MVVM:
  MVVM architecture is used for api calls and room database usage

# CSVWRITER AND CSVREADER:
  This sdk is used to write user searched locations to csv file as favourites 
  and show these location from csv file in favourites.

# WorkManager:
  It is used to show notification at 6 AM with current location temperature.

# Preference Data Store:
  It is used to store data for location name and user selected temperature type.

  UI have three main tabs Forecast, Search and Favourites.
  Forecast tab will show current location weather data with option to view data for five days weather with
  every three houres forecast.
  
  Search tab will show user searched weather with options to add location to favourites.

  Favourites tab has all data from csv file that user added in favourites.

  Searched weather and favourites location weather both have option to see weather for five more days.

  Locations and storage permissions are handled for latest android versions and previous.

  Test cases are implemented.

  Retrofit used for api calls.

  User current location is fetched with FusedLocationProviderClient.

