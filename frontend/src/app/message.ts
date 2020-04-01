import { User } from './user';
import { Task } from './task';

export class Message {
    public id: number;
    public createdAt: Date;
    public createdBy: User;
    public text: string;
    public addressedTo: User;
    public task: Task;

}
