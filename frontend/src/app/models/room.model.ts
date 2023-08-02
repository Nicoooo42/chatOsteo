export interface IRoom {
    id: number;
    horaire_consultation?: string;
    photo?: string
  }
  
  export class Room implements IRoom {
    constructor(public id: number, public horaire_consultation: string, public photo: string) {}
  }
  