import { Message } from './message';
import { Subject } from './subject';
import { User } from './user';

export class Task {
    public id: number;
    public createdAt: Date;
    public createdBy: User;
    public title: string;
    public description: string;
    public place: string;
    public price: string;
    public messages: Message[];
    public subject: Subject;
}
