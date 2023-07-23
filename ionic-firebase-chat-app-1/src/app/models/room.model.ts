export interface IRoom {
    id: number;
    name?: string;
    photo?: string
  }
  
  export class Room implements IRoom {
    constructor(public id: number, public name: string, public photo: string) {}
  }
  