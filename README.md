# Project Title: Pathfinding Algorithms & Minimum Spanning Tree

## Team Members
- **Varshith(23BDS011)**
- **Ganesh(23BDS024)**
- **Izhaar Ahmed(23BDS053)**

## Project Overview

This repository contains two distinct yet related projects that involve graph algorithms. The first project implements classical pathfinding algorithms (BFS, DFS, and A*) on a large-scale dataset representing Pennsylvania roadways. The second project implements Kruskal's algorithm to compute the Minimum Spanning Tree (MST) using a dataset representing Mumbai stations as nodes and their connections as edges.

### Project 1: Pathfinding Algorithms on Pennsylvania Roadways

In this project, we implement the following graph search algorithms:
- **Breadth-First Search (BFS)**
- **Depth-First Search (DFS)**
- **A Star Algorithm**

These algorithms are applied to a dataset containing **800,000 datapoints** that represent various roadways and connections in Pennsylvania. The goal is to simulate navigation across the roadways, analyzing paths, distances, and connections between different nodes (representing locations).

### Project 2: Kruskal's Algorithm for Minimum Spanning Tree (MST)

In the second project, we implement **Kruskal's algorithm** to find the Minimum Spanning Tree (MST) of a graph. The graph is modeled with **Mumbai stations** as nodes and **railway connections** as edges. The dataset is input through a file dialog using `JFileChooser`, and the edges are read using **BufferedReader** to dynamically load the graph data. The algorithm computes the MST, which represents the least-cost way to connect all stations with the fewest connections (edges).

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Cloning the Repository](#cloning-the-repository)
3. [Setting Up the Environment](#setting-up-the-environment)
4. [Compiling and Running the Project via Command Prompt](#compiling-and-running-the-project-via-command-prompt)
5. [Project 1: Pathfinding Algorithms](#project-1-pathfinding-algorithms)
    - [Running the Algorithms](#running-the-algorithms)
    - [Dataset Overview](#dataset-overview)
6. [Project 2: Kruskal's Algorithm](#project-2-kruskals-algorithm)
    - [Running Kruskal's Algorithm](#running-kruskals-algorithm)
    - [Dataset Overview](#dataset-overview-1)
7. [Directory Structure](#directory-structure)
8. [Contributing](#contributing)
9. [License](#license)

## Prerequisites

Before you begin, ensure that you have the following software installed:

- **Java Development Kit (JDK)** version 8 or higher.
- **Git** (to clone the repository).

### Install Java Development Kit (JDK)

To run Java programs from the command prompt, you need to have the JDK installed on your system.

1. **Download JDK**:
    - Visit the official JDK download page: [Download JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (or use OpenJDK if preferred).
    - Choose the appropriate version for your operating system.

2. **Install JDK**:
    - Follow the installation instructions provided for your operating system.
    - Once installed, you need to set up the **Java environment variables**.

#### Set Up Java Environment Variables on Windows

1. **Find JDK Installation Path**: By default, the JDK will be installed in a path similar to `C:\Program Files\Java\jdk-11.0.x`.

2. **Set JAVA_HOME**:
    - Right-click on "This PC" or "My Computer" and click on **Properties**.
    - Click on **Advanced System Settings** > **Environment Variables**.
    - Under **System variables**, click on **New** and add:
        - **Variable Name**: `JAVA_HOME`
        - **Variable Value**: the path to your JDK folder (e.g., `C:\Program Files\Java\jdk-11.0.x`).

3. **Update PATH**:
    - In the **System variables** section, find the `Path` variable and click **Edit**.
    - Add a new entry: `%JAVA_HOME%\bin`.

4. **Verify Java Installation**:
   Open the command prompt and type:

   ```bash
   java -version
