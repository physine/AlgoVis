
single window:
- n * m grid
- a way a varying the speed (a scale between 0 and 1)
- a way to mark the starting cell and the final cell
- a way to mark the cells obstacles

view -> controller -> model


state machines:

- gridStates
  - preSearchState
  - searchingState
  - postSearchState
  
  + start button
  + reset button 
  
- CellMarkerStates
  - noneSelectedState
  - startCellSelectedState
  - obstacleCellSelectedState
  - endCellSelectedState

  + start cell marker button
  + obstacle cell marker button
  + end cell marker button
