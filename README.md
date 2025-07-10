# ParkingLot
MVP LLD of a Vehicle Parking Lot

### Future Scope:
1. Only license plate reg no for unparking any vehicle provided no two vehicles have same license reg no
2. Large spot can contain certain no of cars/bikes; Compact spot can contain certain no of bikes
3. Empty spot replaced by Vehicle type with associated license reg no
4. All the admin/configuration details like no of floors, no of spots, parking charge for each vehicle type in a properties file
6. <b>Locking mechanism for simultaneous parking request by multiple vehicles(Threading)</b>
7. Goal is to maximise the profit by the parking lot owner
8. Parking Time:
   Spot Parking - evaluate timestamp from sys-generated parking time
    v/s 
   Pre-book Parking - evaluate timestamp from user input slot (No COD) + Special Discount
  
   Unparking time auto sys-generated always
9. Shuffle the display of parking availability
10. Bill should be auto generated on unparking