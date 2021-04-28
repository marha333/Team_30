# Team_30 - SetRoute

**SetRoute** - self-guided walks and trails for your unique experience of the city. 
It is meant to be a freemium route generating application for **Android**.

<img src="https://i.postimg.cc/nrqsWJHn/route-in-phone.png" width="150">

Among its main features, we should implement:
  
  * Simple UI
  * Filters for route selection (setting, walk duration, season: winter/summer)
  * Chosen route visualization
  * Google Maps integration
  * Anonymous route rating
  * ...

### Practical sessions
24 March 2021 - Team bonding period\
14 April 2021 - Sprint 1\
21 April 2021 - Sprint 1
  
28 April 2021 - Sprint 2\
05 May 2021   - Sprint 2 (1 Release, 23:59)

12 May 2021   - Sprint 3\
19 May 2021   - Sprint 3

26 May 2021   - Sprint 4\
02 May 2021   - Sprint 4\
09 May 2021   - Sprint 4 (2 Release, 23:59)
  
Link to Discord: https://discord.com/invite/sW262YSJkr  

### Team members

| Role                 | Name                  | 
| :---                 | :----:                |
| **Product Owner**    | Darja Bistrova        |
| **Scrum Master**     | Florian Haid          | 
| **Senior Developer** | Johannes Erlacher     |
| **Senior Developer** | Harald Martini        |
| **Developer**        | Bohdan Lailo          |
| **Developer**        | Olha Pavlenko         |
| **Developer**        | Sabine Pils           |
| **Developer**        | Dmytro Shvets         |
| **Developer**        | Kyryll Dasik          |


### Git workflow
1. Fork the repository.
2. Set up the remote upstream `git remote add upstream https://github.com/sw21-tug/Team_30.git`
3. Always pull from the upstream before you start with a new branch `git pull upstream develop`
4. Checkout your current feature branch on the forked repository `git checkout -b SR-XXX`
5. Commit your changes and stick to the commiting convention `git commit -m "SR-XXX <short description>"`

   If you worked in pair programming, don't forget to add your initials! Your initials must be in alphabetical order depending on your first name like in the following example: `git commit -m "SR-XXX <short description> [SJ,DM]"` where SJ stands for Jim SMITH and DM for Mary DOE
   
6. Push your changes to your feature branch and create a pull-request.


## Implemented features
| Ticket #    |              Feature name             | 
| :---        |    :-----------------------------:    |
| SR-014      |            Initial app setup          |
| SR-007      |   Location usage confirmation pop-up  |
| SR-009      |    Main view: List of routes          |
| SR-015      |           Button navigation           |
| SR-010      |           Route description           |
| SR-020      |          Generating Demo Data         |
| SR-022      |           City Map button             |
| SR-024      |      Location usage allowed (Map)     |
