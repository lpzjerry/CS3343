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
├── ems
│   ├── Branch.java
│   ├── CmdAddBranch.java
│   ├── CmdAddCustomer.java
│   ├── CmdAddLinkage.java
│   ├── CmdAddManager.java
│   ├── CmdCheckTime.java
│   ├── CmdCreateOrder.java
│   ├── CmdRmBranch.java
│   ├── CmdRmLinkage.java
│   ├── CmdRmManager.java
│   ├── CmdSearchBranch.java
│   ├── CmdSearchOrder.java
│   ├── Command.java
│   ├── Company.java
│   ├── Customer.java
│   ├── Dijkstra.java
│   ├── Edge.java
│   ├── Graph.java
│   ├── Invoker.java
│   ├── Main.java
│   ├── Manager.java
│   ├── Order.java
│   ├── OrderPool.java
│   ├── Position.java
│   ├── Receiver.java
│   └── Sender.java
└── ems_test
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── BranchPosIntegrationTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── BranchTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── CompanyTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── CourierTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── CustomerCompanyTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── CustomerTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── EdgeIntegrationTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── EdgeTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── GraphTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── ManagerTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── OrderBranchPositionTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── OrderPoolOrderTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── OrderPoolTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── OrderTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── PositionTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── ReceiverTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── SenderTest.java
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── TestBranch.java


## Sample Cmd Line Rundown

> manager 0
> addManager manager1 123456 female 1
> addBranch branch1 1 2
> searchBranch 1 2
> addBranch branch2 15 15
> addCustomer sender 654321 1 2 2
> addCustomer receiver 567890 1 15 14
> addLink 1 2
> logout
> customer 0
> createOrder order 1
> searchOrder 1
> searchOrder order
> logout
> manager 0
> rmManager 1
> rmBranch 1
> exit 



