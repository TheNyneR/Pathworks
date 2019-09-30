[![Build Status](https://travis-ci.org/Oliveshark/Pathworks.svg?branch=master)](https://travis-ci.org/Oliveshark/Pathworks)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/038e2e56004d41b48866b61109a93ef9)](https://www.codacy.com/manual/LiquidityC/Pathworks?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Oliveshark/Pathworks&amp;utm_campaign=Badge_Grade)
[![CodeFactor](https://www.codefactor.io/repository/github/oliveshark/pathworks/badge)](https://www.codefactor.io/repository/github/oliveshark/pathworks)

# Pathworks
A tool for running and testing path finding algorithms.

## Idea
I was looking around for a decent way to test some cooperative path finding algorithms
before implementing them in a game that I'm working on.

Since I couldn't find any decent easy to use ones I figured it couldn't be to hard
to implement this on my own.

So this program should operate as a test bed for running some pre-written path finding algorithms
as well as implementing your own algorithms and testing them in this graphical environment.

## Solution
This software should provide an editable grid that makes out the 'play area'
clicking cells should toggle them between the states 'occupied' and 'walkable'.

There should be an option to add 'agents' to the playing field as well as giving each agent
a destination that it will try to get to.

When the algorithm runs the agents should move in real-time, not steps. I don't think
actual collision resolution between walls and agents should be needed since agents shouldn't
try to walk on occupied space.

However collisions between agents and agents need to be done to
be able to show when algorithms aren't performing well. It doesn't need to be a pretty solution though.

### Code
I decided to go with Java and libGDX. In part because I'd like to practice a bit with it. Java is
more accessible to many devs, I think. Don't quote me on that. It's also easier to setup the project on
different platforms and easier to define an "algorithm" interface.

This project isn't intended to provide an executable to use. Rather it provides an easy to setup and run
project that you may then go to town on testing your algorithms. If you intend to use it in a game it should be
rather straight forward to 'port' the code to your own project.

## Building
Follow this link to get details on how to launch a libGDX project in your preferred dev environment.
You need gradle and stuff, but all that is rather self explanatory.

[Running and debugging libGDX projects](https://libgdx.badlogicgames.com/documentation/gettingstarted/Running%20and%20Debugging.html)

**NOTE** I've been unable to compile with any version above java 8. Can't be
bothered to figure it out. This is a heads up if you run into problems when
compiling.

## Contribute
Yes please. You are welcome to create issues and begin work on them or pick one from the list of existing issues.
More details can be found here: [CONTRIBUTING.md](CONTRIBUTING.md)

**Important!!**
If you do contribute and want to add your name to your work then don't forget to add your name to the [CONTRIBUTORS.md](CONTRIBUTORS.md) file.
This is optional if you want to be private. But it's fun to see who helped out when it's done. I hope there will be time to make this information
visible within the framework.

## Hacktoberfest
Since this coincides nicely with [hacktoberfest](https://hacktoberfest.digitalocean.com/) I decided to make this project
quite a nice and easy project to get into and start hacking. I'll create tasks for everything and tag them appropriately.
