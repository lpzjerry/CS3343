# Development Issues 
#### *raised up by Pengze Liu*

### dependency issue
Any Customer instead of the Sender/Receiver could access to any packages if they have the order ID.
- Solution: Orders keep track of sender & receiver. Check accessibility before operations enabled.

### modified logic
- Branch.checkOut(): if Courier.capacity is not fulfilled, **Push all the queuing Orders to the "thingsToSend"**
    **regardless of the destination**
    also, the branch would not maintain the onDelivery Order
- Branch.checkIn(): add to queue

### optional functionalities not implemented
  Sender
    +changeDestination(int orderID, Position newPosition);  
    +askToWithdrawOrder(int orderID);  
  Order  
    -prize  
    -priority  
    - <Position>path  
  Company  
    -companyClock  
    
    
    
    
------
_patrick_
### Alert!!

* For demo purpose, we let newly created branch's location to be randomly assigned.

