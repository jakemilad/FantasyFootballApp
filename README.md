# My Personal Project

## Introduction

For my personal project, I intend to make a **Fantasy Soccer Application**. This project idea was of interest to me 
because for the last five years I have actively played *Fantasy Premier League*; an online points-based Fantasy sports
game based on the *English Premier League*. I developed quite a passion for it over time after consistently finishing
in the top 2% of players globally amongst an estimated 11 million. Moreover, there was a proposal in world football 
regarding a "Super League", which combined Europe's top soccer teams all into one league, which created major
controversy amongst Soccer fans, this application will essentially highlight what it would look like if the
"Super League" was actually created.

## Functionality
The application have a preloaded set of top Soccer players across Europe's top five leagues. The user will be able to 
add players into their team, constrained by a maximum number of players per team, and maximum number of players per
position (Defense, Midfield, Attack). The positive points that the players accumulate is based on statistics such as:
- Goals
- Assists
- Clean sheets 
- minutes played

Whilst point deduction statistics would be 
- Yellow cards
- Red cards
- Fouls
- Own goals
- Penalty misses

The user adds their desired players into their team, and the stats for each game-week are updated based on the
aforementioned statistics, and a game-week points is generated for  the users team. For example, allocating 3 points
for a goal, 2 points for an assist, a users players scores 2 goals and gets 1 assist, therefore accumulates 8 points
for that game-week.


## User Stories

- As a user, be able to create a team with a team name and add a list of players to the team.
- As a user, be able to create a league and add to a list of teams to the league.
- As a user, be able to remove and add players from the team.
- As a user, be able to have access to the entire list of players to choose from.
- As a user, be able to update the statistics of each player such as goals and assists.
- As a user, be able to view the total points accumulated for each team in the list of teams.
- As a user, be able to continuously accumulate points.
- As a user, be able to view all players available.
- As a user, be able to save my league table points as well as my player points.
- As a user, be able to load my saved league table points as well as my player points.

## Phase 4: Task 2
Tue Nov 23 23:16:59 PST 2021
Added Messi to Jake's team.

Tue Nov 23 23:17:06 PST 2021
Added Ronaldo to Paul's team.

Tue Nov 23 23:17:17 PST 2021
Messi scored 2 goal(s) for Jake's team.

Tue Nov 23 23:17:18 PST 2021
Ronaldo scored 3 goal(s) for Paul's team.

Tue Nov 23 23:17:19 PST 2021
Messi assisted 1 time(s) for  Jake's team.

Tue Nov 23 23:17:20 PST 2021
Ronaldo assisted 2 time(s) for  Paul's team.

Tue Nov 23 23:17:23 PST 2021
Neymar scored 3 goal(s).

Tue Nov 23 23:17:26 PST 2021
Saka assisted 2 time(s).

Process finished with exit code 0

The addTeam and addPlayerToLeague behaviour will not get logged due to the unidirectional relationship between all
the classes. A League can have 0 or many Players or 0 or many Teams, but Players and Teams can only be assigned to 1 
League. Therefore, in the FantasyAppGui, a League only exists as a getter to the list of Teams in the instantiated
League constructor. Therefore, the addTeam or addPlayerToLeague from the League object does not get called because it
never needs to as all Teams and Players can only exist in 1 common league. Therefore, does not qualify as modifying 
"this" and will not be logged.


## Phase 4: Task 3
I am quite satisfied with the construction of my application in terms of the class hierarchy. I think it was very easy 
and logical to follow that a League is a list of Teams and a Team is a list of Players. This unidirectional relationship
made all the associations between these classes much less confusing. Moreover, this was extremely helpful when it came 
to saving and loading with JSon, as I could essentially just write a League, which would contain all the needed 
information to save, which would be dependent on Teams and Players. In terms of the Gui, I would have probably included 
some type of LeagueGui table model which would extract the specific League behaviour from the TeamsGui table model. 
This isn't particularly necessary as the functionality of the TeamsGui table model sufficiently covers the needed 
behaviour of a LeagueGui table model, but it would improve cohesion.
