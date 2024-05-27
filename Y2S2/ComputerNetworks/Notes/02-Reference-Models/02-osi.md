# OSI

- The OSI (Open Systems Interconnection) model is a conceptual framework used to understand and standardize the functions of a telecommunication or computing system without regard to its underlying internal structure and technology.

## Physical Layer

- Manages the physical connection between devices.
- Responsible for the transmission and reception of raw bitstreams over a physical medium.
- Defines the hardware elements involved in the networking, such as cables, switches, network interface cards, and physical network topology.

### Key Components

- Electrical signals, cables, connectors.
- Data rates, signal strength, bit synchronization.

## Data Link Layer

- Provides node-to-node data transfer—a link between two directly connected nodes.
- Detects and possibly corrects errors that may occur in the Physical Layer.
- Manages how data packets are placed on the network (media access control).

### Sub-Layers

- MAC (Media Access Control): Controls how devices on the network gain access to the data and permission to transmit it.
- LLC (Logical Link Control): Manages frame synchronization, flow control, and error checking.

### Key Components

- Frames, MAC addresses, switches, bridges.

## Network Layer

- Manages the delivery of packets across multiple networks.
- Determines the best physical path for data transmission based on network conditions, priority of service, and other factors.
- Handles logical addressing and path determination.

### Key Components

- IP addresses, routers, packet routing.

## Transport Layer

- Ensures complete data transfer.
- Provides error detection and correction, as well as data flow control to avoid congestion.
- Establishes, maintains, and terminates connections between devices.

### Key Components

- Segments (TCP) and datagrams (UDP).
- Port numbers, sockets.

## Session Layer

- Manages sessions or dialogues between applications.
- Establishes, manages, and terminates connections between local and remote applications.
- Provides synchronization and recovery of data exchange.

### Key Components

- Session establishment, maintenance, and termination.
- Checkpointing and recovery, dialog control.

## Presentation Layer

- Translates data between the application layer and the network format.
- Ensures that data is in a usable format and handles data encoding, encryption, and compression.

### Key Components

- Data encryption, data compression.
- Data translation, character encoding (e.g., ASCII, EBCDIC).

## Application Layer

- Closest layer to the end-user.
- Provides network services directly to the user's applications.
- Facilitates communication between software applications and lower-layer network services.

### Key Components

- Application services (e.g., email, file transfer, web browsing).
- Protocols (e.g., HTTP, FTP, SMTP, DNS).
