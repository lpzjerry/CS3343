# CS3343
The group project for CS3343


## Role assignment

1. Product Manager: [WANG Fangzhou](https://github.com/wfz0755), [ZHENG Wanying](https://github.com/SheilaCecilia)
2. Developer Programmer: [QIU Rui](https://github.com/SherlockQiu), [LIU Pengze](https://github.com/lpzjerry)
3. Qulity Assurance Engineer: [WANG Ruochen](http://blog.patrickwang.tech), [FAN Yuyang](https://github.com/YolandaFan), [WANG Yixuan](https://github.com/JinksMI)

## Project topic
**Express management System**.

## Person iteractions

* Customer (who receives items)
* Express company's managers
* Courier (the person who deliver the packages)
* Customer (who sends items)
* Branch offices

## Version Control System

Git

## Install Guide

We used make to automate the compilation process. 


To create structure of the compiled classes, go to CS3343 repo's folder and run the below commands in terminal.  
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
    - Approach (Bottom-up? Sandwich?)
    - (how many test cases)
- Configuration
    - Version control
    - Library / Environmment
- Q&A


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


## Sample Cmd Line Rundown

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


