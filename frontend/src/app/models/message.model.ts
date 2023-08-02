export interface IMessage {
    id: number;
    content?: string;
    room?: string;
    sender?: number;
  }
  
  export class Message implements IMessage{
    constructor(public id: number, public content: string, public room: string, public sender: number) {}
  }
  