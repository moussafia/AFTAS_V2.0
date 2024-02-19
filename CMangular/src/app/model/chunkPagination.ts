export function chunk(array:number[], size:number):number[][]{
    if(size <= array.length)
    return Array.from({length: Math.ceil(array.length/size)},(_,index)=>
    array.slice(index*size, (index + 1)*size));
    else
    return Array.from({length: 1},(u)=> array);
}