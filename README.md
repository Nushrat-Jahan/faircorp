# faircorp
- - - -

### A building management application
- - - -
#### Author : [NUSHRAT JAHAN](https://github.com/Nushrat-Jahan)
##### Project: [Backend project in clever-cloud](http://faircorp-nushrat-jahan.cleverapps.io/)
##### Based on: [Dev Mind spring application](https://dev-mind.fr/formations.html)  

- - - -
## Project Description
#### faircorp is a building management spring application. This application contains four features buildings, rooms, windows and heaters. Building may contain many or zero rooms. Rooms can contain many or zero windows and heaters. Building can show its roomlist. Also, room can show window and heater list as well as can controll them. Room can manage the windows or heaters switch status(ON,OFF or OPEN,CLOSE). These status can also be changed using window or heater id also. All the features can be viewed or created or updated or can be deleted. When the building is deleted all its room, the heaters and windows contained by those rooms will also be deleted. When the room is deleted all the heaters and windows in it will also be deleted.

## Run the project
Clone the project and run the project. At localhost:8080. The login credentials are bellow.

Username: user</br>
Password: password

Successful run of code will show "Welcome to faircorp!!"

## Test Api using swagger 
#### Feature controllers: building-controller, room-controller, window-controller, heater-controller
http://localhost:8080/swagger-ui/index.html

## Used Tools:
Application: spring boot </br>
Database: H2 console</br>
Automation tool: gradle</br> 
IDE: intellij idea</br>

## Implemented APIs
### 1. BUILDING
   -[x] GET/api/buildings : Gets all the buildings list along with room, window and heater information.
   -[x] POST/api/buildings : Creates a new building or update an existing building by building id.
   -[x] GET/api/buildings/{building_id} : Gets individual building information by building id.
   -[x] DELETE/api/buildings/{building_id} : Deletes building by building's id.

### 2. ROOM
   -[x] GET/api/rooms : Gets all the rooms list along with windows and heaters.
   -[x] POST/api/rooms : Creates a new room or update an existing room by room id.
   -[x] GET/api/rooms/{room_id} : Gets individual room's information by room id.
   -[x] DELETE/api/rooms/{room_id} : Deletes individual room by room's id.
   -[x] PUT/api/rooms/{room_id}/switchHeaters : Switches the heaters of a room ON or OFF using that room's room id. 
   -[x] PUT/api/rooms/{room_id}/switchWindows : Switches the windows of a room OPEN or CLOSE using that room's room id.
   -[x] GET/api/rooms/building/{building_id} : Gets all the rooms of a building by that building's building id.

### 3. HEATER
   -[x] GET/api/heaters : Gets all the heaters list along with its information.
   -[x] POST/api/heaters : Creates a new heater or update an existing heater by heater id
   -[x] GET/api/heaters/{heater_id} : Gets individual heater's information by heater id
   -[x] DELETE/api/heaters/{heater_id} : Deletes individual heater by heater's id.
   -[x] PUT/api/heaters/{heater_id}/switch : Switches the heater ON or OFF status
   -[x] GET/api/heaters/room/{room_id} : Gets all the heaters of a room by that room's room id.

### 4. WINDOW
   -[x] GET/api/windows : Gets all the windows list along with its information.
   -[x] POST/api/windows : Creates a new window or update an existing window by window id.
   -[x] GET/api/windows/{window_id} : Gets individual window's information by window id
   -[x] DELETE/api/windows/{window_id} : Deletes individual window by window's id.
   -[x] PUT/api/windows/{window_id}/switch : Switches the window OPEN or CLOSE status.
   -[x] GET/api/windows/room/{room_id} : Gets all the windows of a room by that room's room id.