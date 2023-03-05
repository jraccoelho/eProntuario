export class Util {
  validateTk(): boolean {
    return (localStorage.getItem('tk')) ? true : false;

  }
}
