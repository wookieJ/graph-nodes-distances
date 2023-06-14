import {useRef} from 'react'
import {Graph, useLink, useNode} from 'graphire'


export default function App() {
    return (
        <svg style={{width: '100vw', height: '100vh'}}>
            <Graph dim={2}>
                <g id='nodes'>
                    <Node uid={'0'} x={110} y={300}/>
                    <Node uid={'1'} x={50} y={30}/>
                    <Node uid={'2'} x={150} y={80}/>
                    <Node uid={'3'} x={400} y={100}/>
                </g>
                <Link source={'0'} target={'1'} color='red'/>
                <Link source={'1'} target={'2'}/>
                <Link source={'0'} target={'2'}/>
                <Link source={'3'} target={'1'}/>
                <use href="#nodes"/>
            </Graph>
        </svg>
    );
}

const Node = (props: any) => {
    const {color = 'white', radius = 15, ...rest} = props
    const ref: any = useRef()
    useNode(([cx, cy]) => {
        ref.current.setAttribute('cx', cx)
        ref.current.setAttribute('cy', cy)
    }, rest)
    return <circle ref={ref} cx='0' cy='0' r={radius} fill={color}/>
}

const Link = (props: any) => {
    const {source, target, color = 'blue', ...rest} = props
    const ref: any = useRef()

    useLink(([x1, y1], [x2, y2]) => {
        ref.current.setAttribute('x1', x1)
        ref.current.setAttribute('y1', y1)
        ref.current.setAttribute('x2', x2)
        ref.current.setAttribute('y2', y2)
    }, source, target, rest)
    return (
        <line ref={ref} x1='0' y1='0' x2='0' y2='0' stroke={color} strokeWidth={2}/>
    )
}
