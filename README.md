# Search Algorithm Visualizer

## Overview

This Java application visually demonstrates how search algorithms such as Depth-First Search (DFS), Breadth-First Search (BFS), Dijkstra algo, and A-Star navigate through a grid. It's designed to provide an interactive and educational tool for understanding these algorithms' behavior and characteristics.

## Features

- **Interactive Grid:** Visualize search algorithms on a customizable grid.
- **Algorithm Selection:** Choose between different algorithms like DFS and BFS.
- **Real-Time Visualization:** Watch how algorithms explore the grid, represented with color shifts indicating the depth or order of exploration.
- **Obstacle Creation:** Place obstacles on the grid to test how algorithms navigate around them.
- **Start and End Points:** Set the starting and ending points for the search.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven (for building and managing dependencies)

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/physine/AlgoVis.git
    cd AlgoVis
    ```

2. **Build the project:**

    ```bash
    mvn clean install
    ```

3. **Run the application:**

    ```bash
    java -jar target/algorithm-visualizer.jar
    ```

### Usage

- Launch the application to see the grid.
- Select an algorithm from the dropdown menu.
- Click on the grid to set start and end points, and to create obstacles.
- Click `Start` to begin the visualization.

## Contributing

Contributions to the project are welcome! Here's how you can help:

- **Reporting Bugs:** Open an issue explaining the bug and steps to reproduce it.
- **Suggesting Enhancements:** Open an issue with your suggestions for improvements.
- **Pull Requests:** For significant changes, please open an issue first to discuss what you would like to change. Ensure to update tests as appropriate.
