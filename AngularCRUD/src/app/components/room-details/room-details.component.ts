import { Component, Input, OnInit } from '@angular/core';
import { Room } from 'src/app/models/room.model';
import { RoomService } from 'src/app/services/room.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-room-details',
    templateUrl: './room-details.component.html',
    styleUrls: ['./room-details.component.css']
})
export class RoomDetailsComponent implements OnInit {
    @Input() viewMode = false;
    @Input() currentRoom: Room = {
    name: '',
    capacity: 0,
    pricePerHour: 0,
    isReserved: false,
    reunionType: ''
    };
    
    message = '';

    constructor(
        private roomService: RoomService,
        private route: ActivatedRoute,
        private router: Router
    ) { }

    ngOnInit(): void {
        if (!this.viewMode) {
        this.message = '';
        this.getRoom(this.route.snapshot.params['id']);
        }
    }

    getRoom(id: string): void {
        this.roomService.get(id)
        .subscribe({
            next: (data) => {
            this.currentRoom = data;
            },
            error: (e) => console.error(e)
        });
    }

    updateReserved(status: boolean): void {
        const data = { ...this.currentRoom, isReserved: status };

        this.message = '';

        this.roomService.update(this.currentRoom.id, data)
        .subscribe({
            next: (res) => {
            this.currentRoom.isReserved = status;
            this.message = res.message ? res.message : 'El estado ha sido actualizado.';
            },
            error: (e) => console.error(e)
        });
    }

    updateRoom(): void {
        this.message = '';

        this.roomService.update(this.currentRoom.id, this.currentRoom)
        .subscribe({
            next: (res) => {
            this.message = res.message ? res.message : '¡Sala actualizada con éxito!';
            },
            error: (e) => console.error(e)
        });
    }

    deleteRoom(): void {
        this.roomService.delete(this.currentRoom.id)
        .subscribe({
            next: (res) => {
            console.log(res);
            this.router.navigate(['/rooms']);
            },
            error: (e) => console.error(e)
        });
    }
}