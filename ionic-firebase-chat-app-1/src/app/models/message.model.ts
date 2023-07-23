export interface IMessage {
    id: number;
    content?: string;
  }
  
  export class Message implements IMessage{
    constructor(public id: number, public content: string) {}
  }
  