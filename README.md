# Express management System

## Role assignment

1. Product Manager: [WANG Fangzhou](https://github.com/wfz0755), [ZHENG Wanying](https://github.com/SheilaCecilia)
2. Developer Programmer: [QIU Rui](https://github.com/SherlockQiu), [LIU Pengze](https://github.com/lpzjerry)
3. Qulity Assurance Engineer: [WANG Ruochen](http://blog.patrickwang.tech), [FAN Yuyang](https://github.com/YolandaFan), [WANG Yixuan](https://github.com/JinksMI)


## Install Guide

We used [Make](https://www.gnu.org/software/make/manual/make.html) to automate the compilation process. 


#### Prerequisite
- Unix-like environment (i.e. terminal)
- Java SE 1.8

#### Installation in short
Type commands sequentially in terminal,
```
git clone https://github.com/patrickwang96/CS3343.git
cd CS3343/
make new
make build
make jar
```
*Or* simply,
```
TODO 小土你发完official release给个下载jar的链接
```

#### Details
Download the source code from [Project page](https://github.com/patrickwang96/CS3343) and Unzip
*or* download by git clone,
```
git clone https://github.com/patrickwang96/CS3343.git
```

To create structure of the compiled classes, go to CS3343 repo's folder first,
```
cd CS3343/
```

and run the commands below in terminal,  
```
make new 
```

To compile the whole project,    
```
make build
```

To pack all classes into one JAR, 
```
make jar
```

## User Guide
Run the jar file in terminal:
```
java -jar group1.jar
```
The prompt **EMS$** will be displayed if you successfully entered our system.

Our project is a command-driven system, with a user guide command **help**
```
EMS$ help
Cmd:
------------------------------------------Common_Commands------------------------------------------
	help: show the available command
	checkTime: show the time
	searchBranch <x-corodinate> <y-corodinate>: search the Branch by location
	help: show the available command
	exit: exit the system
------------------------------------------End_of_Common_cmd------------------------------------------

------------------------------------------Basic_UI_Commands------------------------------------------
	manager <id>: login as manager with id
	customer <id>: login as customer with id
------------------------------------------End_of_Common_cmd------------------------------------------

------------------------------------------Manager_Commands------------------------------------------
	addBranch <name> <x-corodinate> <y-corodinate>: add branch with name, and position
	addCustomer <name> <password> <priority> <x-corodinate> <y-corodinate>: add customer with infomation
	addLink <source_id> <target_id>: add directed linkage from source branch to target branch
	searchOrder <id>: trace where the order is
	rmBranch <id>: remove branch with id
	rmLink <source_id> <target_id>: remove directed linkage from source branch to target branch
	logout: logout to basic UI command
	[Command only for super maanger]:
		addManager <name> <password> <gender> <rank>: add new manager with the information
		rmManager <id>: remove manager with id
------------------------------------------End_of_Manager_cmd------------------------------------------

------------------------------------------Customer_Commands------------------------------------------
	createOrder <name> <target_id>: create a new order to send
	searchOrder <id>: trace where the order is
	logout: logout to basic UI command
------------------------------------------End_of_Customer_cmd------------------------------------------
```
a sample command rundown is provided below.

## Sample Command Rundown

> help<br>
> manager 0<br>
> addManager manager1 123456 female 1<br>
> addBranch branch1 1 2<br>
> searchBranch 1 2<br>
> addBranch branch2 15 15<br>
> addCustomer sender 654321 1 2 2<br>
> addCustomer receiver 567890 1 15 14<br>
> addLink 1 2<br>
> logout<br>
> customer 0<br>
> createOrder order 1<br>
> searchOrder 1<br>
> searchOrder order<br>
> logout<br>
> manager 0<br>
> rmManager 1<br>
> rmBranch 1<br>
> exit <br>

## Version Control System

Git

## Person iteractions

* Customer (who receives items)
* Express company's managers
* Courier (the person who deliver the packages)
* Customer (who sends items)
* Branch offices

## Project Structure
├── ems  <br>
│   ├── Branch.java <br>
│   ├── CmdAddBranch.java<br>
│   ├── CmdAddCustomer.java<br>
│   ├── CmdAddLinkage.java<br>
│   ├── CmdAddManager.java<br>
│   ├── CmdCheckTime.java<br>
│   ├── CmdCreateOrder.java<br>
│   ├── CmdRmBranch.java<br>
│   ├── CmdRmLinkage.java<br>
│   ├── CmdRmManager.java<br>
│   ├── CmdSearchBranch.java<br>
│   ├── CmdSearchOrder.java<br>
│   ├── Command.java<br>
│   ├── Company.java<br>
│   ├── Customer.java<br>
│   ├── Dijkstra.java<br>
│   ├── Edge.java<br>
│   ├── Graph.java<br>
│   ├── Invoker.java<br>
│   ├── Main.java<br>
│   ├── Manager.java<br>
│   ├── Order.java<br>
│   ├── OrderPool.java<br>
│   ├── Position.java<br>
│   ├── Receiver.java<br>
│   └── Sender.java<br>
└── ems_test<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── BranchPosIntegrationTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── BranchTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── CompanyTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── CourierTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── CustomerCompanyTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── CustomerTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── EdgeIntegrationTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── EdgeTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── GraphTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── ManagerTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── OrderBranchPositionTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── OrderPoolOrderTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── OrderPoolTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── OrderTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── PositionTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── ReceiverTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── SenderTest.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── TestBranch.java<br>


## Presentation structure
- Objective
- Planning
    - Team Organization
    - Development process
    - Project scheduling
- Software
    - Functions
        - Demo
    - System design
        - Design patterns
        - Design principles
    - Code refactoring
    - Bug report
- ※Testing
    - Hierarchy
    - Approach (Bottom-up)
    - Number of test cases
- Configuration
    - Version control
    - Library / Environmment
- Q&A
