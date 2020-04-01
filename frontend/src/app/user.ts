import { Task } from './task';
import { Message } from './message';

export class User {
    public id: number = 0;
    public userName: string;
    public password: string;
    public fullName: string;
    public mail: string;
    public phoneNumber: string;
    public createdAt: Date;
    public createdTasks: Task[];
    public createdMessages: Message[];
    public status: "STUDENT" | "TEACHER";
}
