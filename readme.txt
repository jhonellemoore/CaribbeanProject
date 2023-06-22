Programming Project: 10 Days in the Caribbean

/**********************************************************************
 * Approximate number of hours to complete this assignment            *
 **********************************************************************/
 10 Days in the Caribbean is a command-line game that takes the user through ten days in the region.
 It is modeled after the board game "10 Days in Africa". The main objective of the game is to design
 a trip throughout the Caribbean in 10 days by using different vehicles. The user can visit either countries
 that are adjacent to each other using a boat or countries of the same color using an airplane.
 Overall, the user must fill in the 10 spots in the cardholder with a journey that complies with all the rules
 of the game in order to win the game!

 Check the menuRules.png to see the game's rules
 Check the CaribbeanMap.png to see the map of the countries in the region and their colors and geographic locations

/**********************************************************************
 * Approximate number of hours to complete this assignment            *
 **********************************************************************/

The code for the main file is in Game.java. You can peruse this file to see how everything works on the back end.
The cards are shuffled at the start of the game using an array of integers in Cards.java. The integers from 1 to 26 
correspond to country cards and the integers from 27 to 51 correspond to transportation cards. Everytime a card is pulled, 
