# PaLaborator7

## Students

  - Cojocariu Alexandru, II A2

## Description

In this repository is implemented the logic for positional games, where each player has to choose tokens according to a strategy in order to win.

## Features

  - It has the desired class structure: Token, Board, Player, Game
  - A game interface has to be implemented - describing valid moves and winning conditions
  - The player class implements Runnable
  - The players use a notify-wait system so that only one player moves at a single time. This is done by using a static variable 'currentPlayer'. If your name does not match 'currentPlayer' you keep waiting in the while loop, otherwise you take a token, shift current player, and notifyAll.
  - The games implemented are: arithmetic progression (clique game from bonus is not implemented)
  - A player has to extend a base class for players and to describe he's strategy
  - A timekeeper thread that counts game time and stops the game after some seconds given to the object as parameter
  - A package is used to adress the implementation of finding the longest subsequence that forms an arithmethic progression. I used a naive but simple O(N^4) approach.
