import socket
import pyaudio

UDP_IP = "192.168.1.5"
UDP_PORT = 5000

p = pyaudio.PyAudio()
stream = p.open(format=pyaudio.paInt16, channels=2, rate=44100, output=True)

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((UDP_IP, UDP_PORT))

while True:
    data, addr = sock.recvfrom(4096)
    stream.write(data)
    print(data)
