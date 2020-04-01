import { Difficulty } from './difficulty';
import { Task } from './task';

export class Subject {
    public id: number;
    public name: string;
    public difficulty: Difficulty;
    public tasks: Task[];
}
