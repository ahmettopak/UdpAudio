import socket
import pyaudio

# Set up UDP socket
UDP_IP = "192.168.1.5"  # IP address of the destination host
UDP_PORT = 5000  # Port number to use
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Set up PyAudio
p = pyaudio.PyAudio()
chunk_size = 1024  # Number of audio frames per buffer
sample_rate = 44100  # Sampling rate of the microphone

# Open microphone stream
stream = p.open(format=pyaudio.paInt16, channels=2,
                rate=sample_rate, input=True, frames_per_buffer=chunk_size)

# Send microphone input over UDP
while True:
    data = stream.read(chunk_size)
    sock.sendto(data, (UDP_IP, UDP_PORT))

# # Close the microphone stream and socket
# stream.stop_stream()
# stream.close()
# sock.close()
