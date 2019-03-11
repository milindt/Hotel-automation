# hotel.Hotel-automation
hotel.Hotel automation system for chain of Hotels

A very prestigious chain of hotels is facing a problem of huge consumption of electricity bills for
its electronic equipments. The common equipments, like lights, ACs, etc are currently controlled
manually, by the hotel staff, using manual switches. hotel.Hotel Management wants to optimise the
usage of electricity consumption and also ensure that there is no inconvenience caused to the
guests and staff. So, it has installed Motion Sensors at appropriate places and have approached
you to program a Controller which takes inputs from these sensors and controls various
equipments.
The way the hotel equipments are organised and the requirements for the Controller are listed
below:
1. A hotel.Hotel can have multiple floors
2. Each floor can have multiple main corridors and sub corridors
3. Both main corridor and sub corridor have one light each
4. Both main and sub corridor lights consume 5 units of power when ON
5. Both main and sub corridor have independently controllable ACs
6. Both main and sub corridor ACs consume 10 units of power when ON
7. All the lights in all the main corridors need to be switched ON between 6PM to 6AM,
which is the Night Time slot
8. By default, all ACs are switched ON, all the time
9. When a motion is detected in one of the sub corridors the corresponding lights need to
be switched ON between 6PM to 6AM (Night Time slot)
10. The total power consumption of all the ACs and lights combined should not exceed
(Number of Main corridors * 15) + (Number of sub corridors * 10) units of per floor. Sub
corridor AC could be switched OFF to ensure that the power consumption is not more
than the specified maximum value
11. When there is no motion for more than a minute the sub corridor lights should be
switched OFF and AC needs to be switched ON

Motion in sub-corridors is input to the controller, which needs to keep track and optimise the
power consumption.
Write a program that takes input values for Floors, Main corridors, Sub corridors and takes
different external inputs for motion in sub corridors. For each input, the program prints out the
state of all the lights and ACs in the hotel. For simplicity, assume that the controller is operating
at the Night Time.


Sample input and output below -
● Number of floors: 2
● Main corridors per floor: 1
● Sub corridors per floor: 2
Subsequent Inputs from
Sensors

Output from controller for corresponding sensor input

Default state (when the
program is first run)

hotel.Floor 1

    Main corridor 1 Light 1 : ON AC : ON
    Sub corridor 1 Light 1 : OFF AC : ON
    Sub corridor 2 Light 2 : OFF AC : ON

hotel.Floor 2

    Main corridor 1 Light 1 : ON AC : ON
    Sub corridor 1 Light 1 : OFF AC : ON
    Sub corridor 2 Light 2 : OFF AC : ON

Movement in hotel.Floor 1, Sub
corridor 2

hotel.Floor 1

    Main corridor 1 Light 1 : ON AC : ON
    Sub corridor 1 Light 1 : OFF AC : OFF
    Sub corridor 2 Light 2 : ON​ AC : ON

hotel.Floor 2

    Main corridor 1 Light 1 : ON AC : ON
    Sub corridor 1 Light 1 : OFF AC : ON
    Sub corridor 2 Light 2 : OFF AC : ON

No movement in hotel.Floor 1,
Sub corridor 2 for a minute

hotel.Floor 1

    Main corridor 1 Light 1 : ON AC : ON
    Sub corridor 1 Light 1 : OFF AC : ON
    Sub corridor 2 Light 2 : OFF AC : ON

hotel.Floor 2

    Main corridor 1 Light 1 : ON AC : ON
    Sub corridor 1 Light 1 : OFF AC : ON
    Sub corridor 2 Light 2 : OFF AC : ON

Since the hotel management is trying this for the first time, it would be changing the
requirements as to which electronic equipments are controlled and the criteria based on which
they are controlled. Therefore, the solution design should be flexible enough to absorb these
changes without a need to make significant changes in the program.
