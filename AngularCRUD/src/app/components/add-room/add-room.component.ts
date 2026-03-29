import { Component, OnInit } from '@angular/core';
import { Room } from 'src/app/models/room.model';
import { RoomService } from 'src/app/services/room.service';

@Component({
    selector: 'app-add-room',
    templateUrl: './add-room.component.html',
    styleUrls: ['./add-room.component.css']
})
export class AddRoomComponent implements OnInit {
    room: Room = {
        name: '',
        capacity: 0,
        pricePerHour: 0,
        isReserved: false,
        reunionType: ''
    };
    submitted = false;

    constructor(private roomService: RoomService) { }

    ngOnInit(): void { }

    saveRoom(): void {
    if (!this.room.reunionType || !this.room.name) {
        alert("Broski, rellena todos los campos y selecciona un tipo de evento.");
        return;
    }
    const data = {
        name: this.room.name,
        capacity: this.room.capacity,
        pricePerHour: this.room.pricePerHour,
        reunionType: this.room.reunionType
    };

    this.roomService.create(data)
        .subscribe({
        next: (res) => {
            console.log(res);
            this.submitted = true;
        },
        error: (e) => {
            console.error("Detalle del error 400:", e);
        }
        });
    }

    newRoom(): void {
        this.submitted = false;
        this.room = {
            name: '',
            capacity: 0,
            pricePerHour: 0,
            isReserved: false,
            reunionType: ''
        };
    }
}